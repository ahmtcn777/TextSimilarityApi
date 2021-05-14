package com.otosor.textSearch.Controller;

import com.otosor.textSearch.AppResponse;
import com.otosor.textSearch.EndpointConstants;
import com.otosor.textSearch.Exceptions.CustomException;
import com.otosor.textSearch.Services.TextService;
import com.otosor.textSearch.VO.TextVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin
public class TextController {
    @Autowired
    TextService textService;

    @RequestMapping(value = EndpointConstants.TEXT_ADD, method = RequestMethod.POST)
    public AppResponse saveText(@RequestBody TextVO textVO) throws CustomException {
        try {
            textService.saveText(textVO);
        } catch (CustomException ex) {
            throw new CustomException(ex.getMessage());
        }
        return AppResponse.ok("OK");
    }

    @RequestMapping(value = EndpointConstants.FIND_WRITER, method = RequestMethod.POST)
    public AppResponse findWriter(@RequestBody TextVO textVO) throws CustomException {
        if(Objects.isNull(textVO.getText()) || textVO.getText().isEmpty()){
            throw new CustomException("String is empty!");
        }
        return AppResponse.ok(textService.findWriter(textVO));
    }

    @RequestMapping(value = EndpointConstants.CALCULATE_TWO_TEXT, method = RequestMethod.POST)
    public AppResponse calculateTwoText(@RequestBody List<TextVO> textVOList) throws CustomException {
        if(Objects.isNull(textVOList.get(0).getText()) || textVOList.get(0).getText().isEmpty() ||
                Objects.isNull(textVOList.get(1).getText()) || textVOList.get(1).getText().isEmpty()){
            throw new CustomException("String is empty!");
        }
        return AppResponse.ok(Math.round(textService.similarity(textVOList.get(0).getText(), textVOList.get(1).getText())*100));
    }
}
