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
import io.playdata.probono.model.dto.ActivistDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import springfox.documentation.annotations.ApiIgnore;

//http://localhost:8080/encore/swagger-ui.html#/
@Api(tags = {"Activist"})
@RequiredArgsConstructor 
@RestController
@RequestMapping("/v1/activist")
public class ProbonoSwaggerDoc1 {
	
	private final ProbonoService probonoService;
	
	@ApiOperation(value = "모든 Activist 검색 ", notes = "Probono에 참여하는 기부자 : 모든 기부자 검색")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK !!"),
            @ApiResponse(code = 500, message = "Internal Server Error !"),
            @ApiResponse(code = 404, message = " Not Found !")
    })
    @GetMapping(value = "/activistAll")
    public List<ActivistDTO> getAllActivist() throws Exception {
        return probonoService.getAllActivists();
    }
    
	@ApiOperation(value = "Activist 검색 ", notes = "Probono에 참여하는 기부자 : id로 기부자 검색")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK !!"),
            @ApiResponse(code = 500, message = "Internal Server Error !"),
            @ApiResponse(code = 404, message = " Not Found !")
    })
    @GetMapping(value = "/activist")
    public ActivistDTO getActivist(@ApiParam(value = "activistId" , 
    													   required = true, 
    													   example = "giver1") 
    											 @RequestParam String activistId) throws Exception {
        return probonoService.getActivist(activistId);
    }

    @ApiOperation(value = "추가 Activist 정보 저장", notes = "API 설명 부분 : Activist 한명 저장")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK !!"),
            @ApiResponse(code = 500, message = "Internal Server Error !"),
            @ApiResponse(code = 404, message = " Not Found !")
    })
    @PostMapping(value = "/activistInsert")
    public boolean addActivist(@ApiParam(value = "ActivistDTO", 
			   required = true) @RequestBody ActivistDTO activist, 
    							 @ApiIgnore HttpSession session) throws Exception {
    	return probonoService.addActivist(activist);
    }
    
    @ApiOperation(value = "Activist 정보 수정 ", notes = "API 설명 부분 : Activist id로 해당 Activist의 major 수정 ")
    @ApiResponses({
            @ApiResponse(code = 200, message = "수정 완료 !!"),
            @ApiResponse(code = 500, message = "Internal Server Error !"),
            @ApiResponse(code = 404, message = " Not Found !")
    })
    @RequestMapping(value = "/activistUpdate", method=RequestMethod.POST)
	public boolean updateActivist(@ApiParam(value = "activistId", required = true, example = "giver1") @RequestParam String activistId, 
									@ApiParam(value = "major", required = true, example = "dermatology") @RequestParam String major) throws Exception {
    	return probonoService.updateActivist(activistId, major);															
	}
	
    @ApiOperation(value = "Activist 정보 삭제  ", notes = "API 설명 부분 : Activist id로 해당 Activist의 정보 삭제 ")
    @ApiResponses({
            @ApiResponse(code = 200, message = "삭제 완료 !!"),
            @ApiResponse(code = 500, message = "Internal Server Error !"),
            @ApiResponse(code = 404, message = " Not Found !")
    })
	@RequestMapping(value = "/activistDelete", method={RequestMethod.POST})
	public boolean deleteActivist(@ApiParam(value = "activistId", required = true, example = "giver1")
																@RequestParam String activistId) throws Exception {
    	return probonoService.deleteActivist(activistId);															
	}

}
