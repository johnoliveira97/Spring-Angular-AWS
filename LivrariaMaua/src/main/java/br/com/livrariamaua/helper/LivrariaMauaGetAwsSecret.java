package br.com.livrariamaua.helper;

import com.google.gson.Gson;

import br.com.livrariamaua.exception.AwsSecretsException;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;

public class LivrariaMauaGetAwsSecret {

	private LivrariaMauaGetAwsSecret() {}
	
	public static AwsSecret getSecret() {

	    String secretName = "rds!db-cba21e5f-d460-40d1-8184-09eea78436d6";
	    Region region = Region.of("sa-east-1");

	    SecretsManagerClient client = SecretsManagerClient.builder()
	            .region(region)
	            .build();

	    GetSecretValueRequest getSecretValueRequest = GetSecretValueRequest.builder()
	            .secretId(secretName)
	            .build();

	    GetSecretValueResponse getSecretValueResponse;
	    AwsSecret awsSecret = null;
	    try {
	        getSecretValueResponse = client.getSecretValue(getSecretValueRequest);
	        awsSecret = getPassword(getSecretValueResponse.secretString());
	    } catch (Exception e) {
	        throw new AwsSecretsException("Não foi possível recuperar a senha. ", e);
	    }
	    
	   return awsSecret;
	}
	
	private static AwsSecret getPassword(String secret) {
		Gson gson = new Gson();
		AwsSecret awsSecret = gson.fromJson(secret, AwsSecret.class);
		return awsSecret;
	}

}
