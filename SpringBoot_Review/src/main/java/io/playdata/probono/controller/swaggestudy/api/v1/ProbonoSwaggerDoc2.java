package io.playdata.probono.controller.swaggestudy.api.v1;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.playdata.probono.model.ProbonoService;
import io.playdata.probono.model.dto.RecipientDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import springfox.documentation.annotations.ApiIgnore;

//http://localhost:8080/encore/swagger-ui.html#/
@Api(tags = {"Recipient"})
@RequiredArgsConstructor 
@RestController
@RequestMapping("/v1/recipient")
public class ProbonoSwaggerDoc2 {
	
	private final ProbonoService probonoService;
	
	@ApiOperation(value = "모든 Recipient 검색 ", notes = "Probono에 참여하는 수혜자 : 모든 수혜자 검색")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK !!"),
            @ApiResponse(code = 500, message = "Internal Server Error !"),
            @ApiResponse(code = 404, message = " Not Found !")
    })
    @GetMapping(value = "/recipientAll")
    public List<RecipientDTO> getAllrecipient() throws Exception {
        return probonoService.getAllRecipients();
    }
    
	@ApiOperation(value = "recipient 검색 ", notes = "Probono에 참여하는 수혜자 : id로 수혜자 검색")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK !!"),
            @ApiResponse(code = 500, message = "Internal Server Error !"),
            @ApiResponse(code = 404, message = " Not Found !")
    })
    @GetMapping(value = "/recipient")
    public RecipientDTO getRecipient(@ApiParam(value = "recipientId" , 
    													   required = true, 
    													   example = "receivePeople1") 
    											 @RequestParam String recipientId) throws Exception {
        return probonoService.getRecipient(recipientId);
    }

    @ApiOperation(value = "추가 recipient 정보 저장", notes = "API 설명 부분 : recipient 한명 저장")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK !!"),
            @ApiResponse(code = 500, message = "Internal Server Error !"),
            @ApiResponse(code = 404, message = " Not Found !")
    })
    @PostMapping(value = "/recipientInsert")
    public boolean addrecipient(@ApiParam(value = "RecipientDTO",required = true) @RequestBody RecipientDTO recipient, 
    							 @ApiIgnore HttpSession session) throws Exception {
    	return probonoService.addRecipient(recipient);
    }
    
    @ApiOperation(value = "recipient 정보 수정 ", notes = "API 설명 부분 : recipient id로 해당 recipient의 희망 컨텐츠 수정 ")
    @ApiResponses({
            @ApiResponse(code = 200, message = "수정 완료 !!"),
            @ApiResponse(code = 500, message = "Internal Server Error !"),
            @ApiResponse(code = 404, message = " Not Found !")
    })
    @RequestMapping(value = "/recipientUpdate", method=RequestMethod.POST)
	public boolean updateRecipient(@ApiParam(value = "recipientId", required = true, example = "receivePeople1") @RequestParam String recipientId, 
									@ApiParam(value = "major", required = true, example = " 아토피 치료 ") @RequestParam String receiveHopeContent) throws Exception {
    	return probonoService.updateRecipient(recipientId, receiveHopeContent);															
	}
	
    @ApiOperation(value = "recipient 정보 삭제  ", notes = "API 설명 부분 : recipient id로 해당 recipient의 정보 삭제 ")
    @ApiResponses({
            @ApiResponse(code = 200, message = "삭제 완료 !!"),
            @ApiResponse(code = 500, message = "Internal Server Error !"),
            @ApiResponse(code = 404, message = " Not Found !")
    })
	@RequestMapping(value = "/recipientDelete", method={RequestMethod.POST})
	public boolean deleteRecipient(@ApiParam(value = "recipientId", required = true, example = "receivePeople1")
																@RequestParam String recipientId) throws Exception {
    	return probonoService.deleteRecipient(recipientId);															
	}

}
