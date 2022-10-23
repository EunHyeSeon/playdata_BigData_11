package io.playdata.probono.model;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.playdata.probono.exception.MessageException;
import io.playdata.probono.exception.NotExistException;
import io.playdata.probono.model.dto.ActivistDTO;
import io.playdata.probono.model.dto.ProbonoDTO;
import io.playdata.probono.model.dto.ProbonoProjectDTO;
import io.playdata.probono.model.dto.RecipientDTO;
import io.playdata.probono.model.entity.Activist;
import io.playdata.probono.model.entity.Probono;
import io.playdata.probono.model.entity.ProbonoProject;
import io.playdata.probono.model.entity.Recipient;

@Service
public class ProbonoService {
	
	@Autowired	
	private ActivistDAO activistDAO;	
	
	@Autowired
	private ProbonoDAO probonoDAO; 	
	
	@Autowired
	private ProbonoProjectDAO probonoProjectDAO;	
	
	@Autowired
	private RecipientDAO recipientDAO; 
	
	private ModelMapper mapper = new ModelMapper();
	

	//기부자 id 값으로 한명의 기부자 정보 검색 service
	//entity -> dto로 변환
	public ActivistDTO getActivist(String activistId) throws Exception{
		Optional<Activist> activistEntity = activistDAO.findById(activistId);
		
		if(activistEntity.isEmpty()){
			throw new NotExistException("검색하는 재능 기부자가 존재하지 않습니다.");
		}
		ActivistDTO activist = mapper.map(activistEntity.get(), ActivistDTO.class);
		return activist;
	}
	
	//모든 기부자 검색
	public List<ActivistDTO> getAllActivists() throws Exception{
		List<Activist> activistAll = activistDAO.findAll();
		List<ActivistDTO> activistDTOAll = Arrays.asList(mapper.map(activistAll, ActivistDTO[].class));
		return activistDTOAll;
		
	}

	//모든 프로보노 프로젝 검색 
	public List<ProbonoProjectDTO> getAllProbonoProjects() throws SQLException,NotExistException{
		List<ProbonoProject> probonoProjectAll = probonoProjectDAO.findAll();
		List<ProbonoProjectDTO> probonoProjectDTOAll = Arrays.asList(mapper.map(probonoProjectAll, ProbonoProjectDTO[].class));
		return probonoProjectDTOAll;
	}
	
	//모든 수혜자 검색
	public List<RecipientDTO> getAllRecipients() throws Exception{
		List<Recipient> recipientAll = recipientDAO.findAll();
		List<RecipientDTO> recipientDTOAll = Arrays.asList(mapper.map(recipientAll, RecipientDTO[].class));
		return recipientDTOAll;
	}
	
	//수혜자 id 값으로 검색
	public RecipientDTO getRecipient(String recipientID) throws Exception {
		Optional<Recipient> recipientEntity = recipientDAO.findById(recipientID);
		if(recipientEntity.isEmpty()) {
			throw new NotExistException("검색하는 수혜자가 존재하지 않습니다.");
		}
		RecipientDTO recipientDTO = mapper.map(recipientEntity.get(), RecipientDTO.class);
		return recipientDTO;
	}
	
	//모든 probono 검색
	public List<ProbonoDTO> getAllProbonos() throws Exception {
		List<Probono> probonoAll = probonoDAO.findAll();
		List<ProbonoDTO> probonoDTOAll = Arrays.asList(mapper.map(probonoAll, ProbonoDTO[].class));
		return probonoDTOAll;
	}
	
	public boolean addProbonoProject(ProbonoProjectDTO probonoProject) throws Exception {
		ProbonoProject probonoProjectEntity = mapper.map(probonoProject, ProbonoProject.class);
		if(probonoProjectEntity.getProjectContent() != null && probonoProjectEntity.getProbonoProjectName() != null 
				&& probonoProjectEntity.getActivistId() != null && probonoProjectEntity.getProbonoId() != null ) {
			probonoProjectDAO.save(probonoProjectEntity);
			return true;
		} else {
			throw new MessageException("Probono Project 정보를 확인해주세요. ");
		}
	}
	
	public void notExistActivist(String activistId) throws Exception{
		boolean result = activistDAO.existsById(activistId);
		if(result != true){
			throw new NotExistException("검색하는 재능 기부자가 미 존재합니다.");
		}
	}
	
	public void notExistRecipient(String recipientId) throws Exception{
		boolean result = recipientDAO.existsById(recipientId);
		if(result != true){
			throw new NotExistException("검색하는 재능 수혜자가 미 존재합니다.");
		}
	}
	//기부자 추가 
	public boolean addActivist(ActivistDTO activistDTO) throws Exception {
		if(activistDAO.findById(activistDTO.getId()).isPresent()) {
			throw new MessageException("이미 존재하는 ID입니다. ");
		} else {
			Activist activistEntity = mapper.map(activistDTO, Activist.class);
			activistEntity = activistDAO.save(activistEntity);
			if(activistEntity != null) {
				return true;
			}
		}
		return false;
	}
	//수혜자 추가 
	public boolean addRecipient(RecipientDTO recipientDTO) throws Exception {
		if(recipientDAO.findById(recipientDTO.getId()).isPresent()) {
			throw new MessageException("이미 존재하는 ID 입니다. ");
		}else {
			Recipient recipientEntity = mapper.map(recipientDTO, Recipient.class);
			recipientEntity = recipientDAO.save(recipientEntity);
			if (recipientEntity != null) {
				return true;
			}
		}
		return false;
	}
	
	//기부자 수정 
	@Transactional
	public boolean updateActivist(String activistId, String major) throws Exception {
		notExistActivist(activistId);
		int result = activistDAO.updateActivistByIdMajor(activistId, major);
		if(result == 0){
			throw new MessageException("재능 기부자 정보 갱신 실패");
		}
		return true;	
	}
	
	//기부자 삭제 
	public boolean deleteActivist(String activistId) throws Exception {
		notExistActivist(activistId);
		activistDAO.deleteById(activistId);
		return true;
	}
	
	//수혜자 수정
	@Transactional
	public boolean updateRecipient(String recipientId, String receiveHopeContent) throws Exception {
		notExistRecipient(recipientId);
		int result = recipientDAO.updateRecipientByIdReceiveHopeContents(recipientId, receiveHopeContent);

		if(result == 0){
			throw new MessageException("수혜자 정보 갱신 실패");
		}
		return true;
	}
	
	//수혜자 삭제  
	public boolean deleteRecipient(String recipientId) throws Exception {
		notExistRecipient(recipientId);
		recipientDAO.deleteById(recipientId);
		return true;
	}
}