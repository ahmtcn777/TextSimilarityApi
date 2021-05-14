package com.otosor.textSearch.Services;
import com.otosor.textSearch.Entity.Writer;
import com.otosor.textSearch.Exceptions.CustomException;
import com.otosor.textSearch.Repository.WriterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WriterService {
    @Autowired
    WriterRepository writerRepository;

    public List<Writer> getAllWriters() throws CustomException {
        try{
            return writerRepository.findAll();
        } catch (Exception ex) {
            throw new CustomException(ex.getMessage());
        }
    }

    public Writer saveWriter(Writer writer) throws CustomException {
        try {
            writerRepository.save(writer);
        } catch (Exception ex){
            throw new CustomException(ex.getMessage());
        }
        return writer;
    }

    public Writer getWriterById(Long id) throws CustomException {
        try {
            return writerRepository.getWriterById(id);
        }
        catch (Exception ex){
            throw new CustomException(ex.getMessage());
        }
    }
}
