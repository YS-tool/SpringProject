package com.example.runnerz.run;

import com.example.runnerz.RunnerzApplication;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.asm.TypeReference;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
public class RunJsonDataLoader implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(RunnerzApplication.class);
    private final JdbcClientRunRepository runRepository;
    private final ObjectMapper objectMapper;

    public RunJsonDataLoader(JdbcClientRunRepository runRepository, ObjectMapper objectMapper) {
        this.runRepository = runRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        if (runRepository.count() == 0) {
            try(InputStream in = TypeReference.class.getResourceAsStream("/data/runs.json")) {
                Runs runs = objectMapper.readValue(in, Runs.class);
                log.info("Loading runs");
                runRepository.saveAll(runs.runs());
            }catch(IOException e){
                throw new RuntimeException(e);
            }
        }else{
            log.info(runRepository.count() + " runs found");
        }
    }
}
