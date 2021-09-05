package com.iiht.estock.actuator;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

@Component
public class CustomHealthIndicator extends AbstractHealthIndicator {

	@Override
	protected void doHealthCheck(Health.Builder builder) throws Exception {
		// You can use the builder to display status details
		builder.up().withDetail("Service", "Running").withDetail("Error", "No Error- Healthy status");
	}
}