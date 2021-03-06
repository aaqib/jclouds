/**
 * Licensed to jclouds, Inc. (jclouds) under one or more
 * contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  jclouds licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.jclouds.elb.config;

import java.util.Map;

import org.jclouds.aws.config.FormSigningRestClientModule;
import org.jclouds.elb.ELBAsyncClient;
import org.jclouds.elb.ELBClient;
import org.jclouds.elb.features.InstanceAsyncClient;
import org.jclouds.elb.features.InstanceClient;
import org.jclouds.elb.features.LoadBalancerAsyncClient;
import org.jclouds.elb.features.LoadBalancerClient;
import org.jclouds.elb.features.PolicyAsyncClient;
import org.jclouds.elb.features.PolicyClient;
import org.jclouds.elb.features.AvailabilityZoneAsyncClient;
import org.jclouds.elb.features.AvailabilityZoneClient;
import org.jclouds.rest.ConfiguresRestClient;

import com.google.common.collect.ImmutableMap;
import com.google.common.reflect.TypeToken;

/**
 * Configures the ELB connection.
 * 
 * @author Adrian Cole
 */
@ConfiguresRestClient
public class ELBRestClientModule extends FormSigningRestClientModule<ELBClient, ELBAsyncClient> {
   public static final Map<Class<?>, Class<?>> DELEGATE_MAP = ImmutableMap.<Class<?>, Class<?>> builder()//
            .put(LoadBalancerClient.class, LoadBalancerAsyncClient.class)
            .put(PolicyClient.class, PolicyAsyncClient.class)
            .put(InstanceClient.class, InstanceAsyncClient.class)
            .put(AvailabilityZoneClient.class, AvailabilityZoneAsyncClient.class)
            .build();

   public ELBRestClientModule() {
      super(TypeToken.of(ELBClient.class), TypeToken.of(ELBAsyncClient.class), DELEGATE_MAP);
   }
}
