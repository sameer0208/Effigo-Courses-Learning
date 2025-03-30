package com.effigoproject.spring_batch.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/batch")
public class BatchController {

    private static final Logger logger = LoggerFactory.getLogger(BatchController.class);

    private JobLauncher jobLauncher;

    private Job importUserJob;

    public BatchController(JobLauncher jobLauncher, Job importUserJob)
    {
        this.jobLauncher = jobLauncher;
        this.importUserJob = importUserJob;
    }

    @GetMapping("/start")
    public String startBatch() {
        try {
            JobParameters jobParameters = new JobParametersBuilder()
                    .addLong("runTime", System.currentTimeMillis()) // Ensure unique job execution
                    .toJobParameters();

            logger.info("🚀 Running Batch Job with parameters: {}", jobParameters);

            JobExecution execution = jobLauncher.run(importUserJob, jobParameters);

            logger.info("✅ Batch Job Completed! Status: {}", execution.getStatus());

            return "Batch job started! Status: " + execution.getStatus();
        } catch (Exception e) {
            logger.error("❌ Batch job failed", e);
            return "Batch job failed: " + e.getMessage();
        }
    }
}
