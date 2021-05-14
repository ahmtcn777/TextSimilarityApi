package com.otosor.textSearch.Controller;

import com.otosor.textSearch.AppResponse;
import com.otosor.textSearch.EndpointConstants;
import com.otosor.textSearch.Entity.Writer;
import com.otosor.textSearch.Exceptions.CustomException;
import com.otosor.textSearch.Services.WriterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class WriterController {
    @Autowired
    WriterService writerService;

    @RequestMapping(value = EndpointConstants.WRITER_ADD, method = RequestMethod.POST)
    public AppResponse saveWriter(@RequestBody Writer writer) throws CustomException {
        try{
            writerService.saveWriter(writer);
        } catch (CustomException ex) {
            throw new CustomException(ex.getMessage());
        }
        return AppResponse.ok("OK");
    }

    @RequestMapping(value = EndpointConstants.GET_ALL_WRITERS, method = RequestMethod.GET)
    public AppResponse getAllWriters() throws CustomException {
        try{
            return AppResponse.ok(writerService.getAllWriters());
        } catch (CustomException ex) {
            throw new CustomException(ex.getMessage());
        }
    }
}
