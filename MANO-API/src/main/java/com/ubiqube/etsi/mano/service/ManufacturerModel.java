package com.ubiqube.etsi.mano.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.ubiqube.api.entities.device.Manufacturer;
import com.ubiqube.api.entities.device.Model;
import com.ubiqube.api.exception.ServiceException;
import com.ubiqube.api.interfaces.device.DeviceService;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.exception.NotFoundException;
import javax.annotation.PostConstruct;

/**
 *
 * @author olivier
 *
 */
@Service
public class ManufacturerModel {
	private final DeviceService deviceBean;
	private Map<Long, Manufacturer> manufacturers;

	public ManufacturerModel(final DeviceService _devicebeService) {
		deviceBean = _devicebeService;
	}

	@PostConstruct
	private void init() {
		try {
			manufacturers = deviceBean.getAvailableManufacturers();
		} catch (final ServiceException e) {
			throw new GenericException(e);
		}
	}

	public String getManufacturerById(final String _id) {
		final Manufacturer manufacturer = manufacturers.get(Long.decode(_id));
		if (null == manufacturer) {
			throw new NotFoundException("Manufacturer not found [" + _id + "]");
		}
		return manufacturer.getName();
	}

	public String getModelById(final String _manufacturerId, final String _modelId) {
		final Manufacturer manufacturer = manufacturers.get(Long.decode(_manufacturerId));
		final Model model = manufacturer.getModel(Long.parseLong(_modelId));
		if (null == model) {
			throw new NotFoundException("Model not found [" + _modelId + "]");
		}
		return model.getName();
	}

}
