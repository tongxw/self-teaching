package com.springboot.amazonsqs.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;

// Marker annotation that tells spring to generate bean definitions at runtime for the methods annotated with @Bean annotation.
@Configuration
public class SqsConfig {

	// Value is populated the region code.
	@Value("${cloud.aws.region.static}")
	private String region;

	// Value is populated with the aws access key.
	@Value("${cloud.aws.credentials.access-key}")
	private String awsAccessKey;

	// Value is populated with the aws secret key
	@Value("${cloud.aws.credentials.secret-key}")
	private String awsSecretKey;

	// @Bean annotation tells that a method produces a bean which is to be managed by the spring container.
	@Bean
	public QueueMessagingTemplate queueMessagingTemplate() {
		return new QueueMessagingTemplate(amazonSQSAsync());
	}

	@Bean
	// @Primary annotation gives a higher preference to a bean (when there are multiple beans of same type).
	@Primary
	// AmazonSQSAsync is an interface for accessing the SQS asynchronously. 
	// Each asynchronous method will return a Java Future object representing the asynchronous operation.
	public AmazonSQSAsync amazonSQSAsync() {
		return AmazonSQSAsyncClientBuilder
				.standard()
				.withRegion(region)
				.withCredentials(new AWSStaticCredentialsProvider(
						new BasicAWSCredentials(awsAccessKey, awsSecretKey)))
				.build();
	}
}
