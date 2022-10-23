package io.playdata.probono.controller.swaggestudy.api.v1;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.playdata.probono.model.ProbonoService;
import io.playdata.probono.model.dto.ProbonoDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

//http://localhost:8080/encore/swagger-ui.html#/
@Api(tags = {"Probono"})
@RequiredArgsConstructor 
@RestController
@RequestMapping("/v1/probono")
public class ProbonoSwaggerDoc4 {
	
	private final ProbonoService probonoService;
	
	@ApiOperation(value = "모든 Probono 검색 ", notes = "모든 Probono 검색")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK !!"),
            @ApiResponse(code = 500, message = "Internal Server Error !"),
            @ApiResponse(code = 404, message = " Not Found !")
    })
    @GetMapping(value = "/probonoAll")
    public List<ProbonoDTO> getAllProbonoProject() throws Exception {
        return probonoService.getAllProbonos();
    }
    


}
