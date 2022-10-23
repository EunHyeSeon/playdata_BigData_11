package io.playdata.probono.controller.swaggestudy.api.v1;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.playdata.probono.model.ProbonoService;
import io.playdata.probono.model.dto.ProbonoProjectDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import springfox.documentation.annotations.ApiIgnore;

//http://localhost:8080/encore/swagger-ui.html#/
@Api(tags = {"ProbonoProject"})
@RequiredArgsConstructor 
@RestController
@RequestMapping("/v1/probonoProject")
public class ProbonoSwaggerDoc3 {
	
	private final ProbonoService probonoService;
	
	@ApiOperation(value = "모든 Probono Project 검색 ", notes = "모든 Probono Project 검색")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK !!"),
            @ApiResponse(code = 500, message = "Internal Server Error !"),
            @ApiResponse(code = 404, message = " Not Found !")
    })
    @GetMapping(value = "/probonoProjectAll")
    public List<ProbonoProjectDTO> getAllProbonoProject() throws Exception {
        return probonoService.getAllProbonoProjects();
    }
    

    @ApiOperation(value = "추가 Probono Project 정보 저장", notes = "API 설명 부분 : ProbonoProject 추가 저장")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK !!"),
            @ApiResponse(code = 500, message = "Internal Server Error !"),
            @ApiResponse(code = 404, message = " Not Found !")
    })
    @PostMapping(value = "/probonoProjectInsert")
    public boolean addProbonoProject(@ApiParam(value = "ProbonoProjectDTO",required = true) @RequestBody ProbonoProjectDTO probonoProject, 
    							 @ApiIgnore HttpSession session) throws Exception {
    	return probonoService.addProbonoProject(probonoProject);
    }
    

}
