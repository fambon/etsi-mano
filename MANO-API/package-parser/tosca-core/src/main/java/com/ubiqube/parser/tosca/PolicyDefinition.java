package com.ubiqube.parser.tosca;

import java.util.List;
import java.util.Map;

public class PolicyDefinition {
	private String name;
	private String type;
	private String description;
	private Map<String, String> metadata;
	// Normally it should be a ToscaProperties.
	private Map<String, Object> properties;
	private List<String> targets;
	private Map<String, TriggerDefinition> triggers;

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(final String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public Map<String, String> getMetadata() {
		return metadata;
	}

	public void setMetadata(final Map<String, String> metadata) {
		this.metadata = metadata;
	}

	public Map<String, Object> getProperties() {
		return properties;
	}

	public void setProperties(final Map<String, Object> properties) {
		this.properties = properties;
	}

	public List<String> getTargets() {
		return targets;
	}

	public void setTargets(final List<String> targets) {
		this.targets = targets;
	}

	public Map<String, TriggerDefinition> getTriggers() {
		return triggers;
	}

	public void setTriggers(final Map<String, TriggerDefinition> triggers) {
		this.triggers = triggers;
	}

}
