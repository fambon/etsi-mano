/**
 *     Copyright (C) 2019-2020 Ubiqube.
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.ubiqube.etsi.mano.vnfm.v261.controller.vnflcm.sol002;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.ubiqube.etsi.mano.common.v261.model.Link;
import com.ubiqube.etsi.mano.dao.mano.Subscription;
import com.ubiqube.etsi.mano.dao.mano.subs.SubscriptionType;
import com.ubiqube.etsi.mano.service.SubscriptionService;
import com.ubiqube.etsi.mano.service.event.Notifications;
import com.ubiqube.etsi.mano.vnfm.v261.model.nslcm.LccnSubscription;
import com.ubiqube.etsi.mano.vnfm.v261.model.nslcm.LccnSubscriptionLinks;
import com.ubiqube.etsi.mano.vnfm.v261.model.nslcm.LccnSubscriptionRequest;

import ma.glasnost.orika.MapperFacade;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-12-11T18:14:17.202+01:00")

@Controller
public class VnflcmSubscriptions261Sol002Controller implements VnflcmSubscriptions261Sol002Api {

	private final SubscriptionService subscriptionService;

	private final MapperFacade mapper;

	private final Notifications notifications;

	public VnflcmSubscriptions261Sol002Controller(final SubscriptionService _subscriptionService, final MapperFacade _mapper, final Notifications _notifications) {
		subscriptionService = _subscriptionService;
		mapper = _mapper;
		notifications = _notifications;
	}

	@Override
	public ResponseEntity<List<LccnSubscription>> subscriptionsGet(@Valid final String filter) {
		final List<Subscription> list = subscriptionService.query(filter, SubscriptionType.VNFLCM);
		final List<LccnSubscription> pkgms = mapper.mapAsList(list, LccnSubscription.class);
		pkgms.stream().forEach(x -> x.setLinks(createSubscriptionsLinks(x.getId())));
		return ResponseEntity.ok(pkgms);
	}

	@Override
	public ResponseEntity<LccnSubscription> subscriptionsPost(@Valid final LccnSubscriptionRequest lccnSubscriptionRequest) {
		Subscription subscription = mapper.map(lccnSubscriptionRequest, Subscription.class);
		notifications.check(subscription.getAuthentificationInformations(), subscription.getCallbackUri());
		subscription = subscriptionService.save(subscription, SubscriptionType.VNFLCM);
		final LccnSubscription pkgmSubscription = mapper.map(subscription, LccnSubscription.class);
		pkgmSubscription.setLinks(createSubscriptionsLinks(pkgmSubscription.getId()));
		return new ResponseEntity<>(pkgmSubscription, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<Void> subscriptionsSubscriptionIdDelete(final String subscriptionId) {
		subscriptionService.delete(UUID.fromString(subscriptionId), SubscriptionType.VNFLCM);
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<LccnSubscription> subscriptionsSubscriptionIdGet(final String subscriptionId) {
		final Subscription subscription = subscriptionService.findById(UUID.fromString(subscriptionId), SubscriptionType.VNFLCM);
		final LccnSubscription pkgmSubscription = mapper.map(subscription, LccnSubscription.class);
		pkgmSubscription.setLinks(createSubscriptionsLinks(subscriptionId));
		return new ResponseEntity<>(pkgmSubscription, HttpStatus.OK);
	}

	private static LccnSubscriptionLinks createSubscriptionsLinks(@NotNull final String id) {
		final LccnSubscriptionLinks links = new LccnSubscriptionLinks();
		final Link self = new Link();
		self.setHref(linkTo(methodOn(VnflcmSubscriptions261Sol002Api.class).subscriptionsSubscriptionIdGet(id)).withSelfRel().getHref());
		links.setSelf(self);
		return links;
	}

}
