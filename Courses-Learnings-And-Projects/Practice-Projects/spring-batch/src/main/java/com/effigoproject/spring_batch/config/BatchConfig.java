package com.effigoproject.spring_batch.config;

import com.effigoproject.spring_batch.constants.AppConstants;
import com.effigoproject.spring_batch.entity.Users;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BatchConfig {
    @Bean
    public Job importUserJob(JobRepository jobRepository, Step step1) {
        return new JobBuilder("importUserJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(step1)
                .build();
    }

    @Bean
    public Step step1(JobRepository jobRepository, PlatformTransactionManager transactionManager,
                      ItemReader<Users> reader, ItemProcessor<Users, Users> processor, ItemWriter<Users> writer) {

        return new StepBuilder("step1", jobRepository)
                .<Users, Users>chunk(AppConstants.chunkSize, transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .faultTolerant()
                .skip(Exception.class)
                .skipLimit(0)
                .build();
    }
}
