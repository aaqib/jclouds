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
package org.jclouds.rds.features;

import static org.jclouds.aws.reference.FormParameters.ACTION;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.jclouds.aws.filters.FormSigner;
import org.jclouds.collect.PaginatedIterable;
import org.jclouds.rds.domain.SecurityGroup;
import org.jclouds.rds.options.ListSecurityGroupsOptions;
import org.jclouds.rds.xml.DescribeDBSecurityGroupsResultHandler;
import org.jclouds.rds.xml.SecurityGroupHandler;
import org.jclouds.rest.annotations.ExceptionParser;
import org.jclouds.rest.annotations.FormParams;
import org.jclouds.rest.annotations.RequestFilters;
import org.jclouds.rest.annotations.VirtualHost;
import org.jclouds.rest.annotations.XMLResponseParser;
import org.jclouds.rest.functions.ReturnNullOnNotFoundOr404;
import org.jclouds.rest.functions.ReturnVoidOnNotFoundOr404;

import com.google.common.util.concurrent.ListenableFuture;

/**
 * Provides access to Amazon RDS via the Query API
 * <p/>
 * 
 * @see <a href="http://docs.amazonwebservices.com/AmazonRDS/latest/APIReference"
 *      >doc</a>
 * @see SecurityGroupClient
 * @author Adrian Cole
 */
@RequestFilters(FormSigner.class)
@VirtualHost
public interface SecurityGroupAsyncClient {
 
   /**
    * @see SecurityGroupClient#get()
    */
   @POST
   @Path("/")
   @XMLResponseParser(SecurityGroupHandler.class)
   @FormParams(keys = "Action", values = "DescribeDBSecurityGroups")
   @ExceptionParser(ReturnNullOnNotFoundOr404.class)
   ListenableFuture<SecurityGroup> get(@FormParam("DBSecurityGroupName") String name);

   /**
    * @see SecurityGroupClient#list()
    */
   @POST
   @Path("/")
   @XMLResponseParser(DescribeDBSecurityGroupsResultHandler.class)
   @FormParams(keys = "Action", values = "DescribeDBSecurityGroups")
   ListenableFuture<PaginatedIterable<SecurityGroup>> list();

   /**
    * @see SecurityGroupClient#list(ListSecurityGroupsOptions)
    */
   @POST
   @Path("/")
   @XMLResponseParser(DescribeDBSecurityGroupsResultHandler.class)
   @FormParams(keys = "Action", values = "DescribeDBSecurityGroups")
   ListenableFuture<PaginatedIterable<SecurityGroup>> list(ListSecurityGroupsOptions options);

   /**
    * @see SecurityGroupClient#delete()
    */
   @POST
   @Path("/")
   @ExceptionParser(ReturnVoidOnNotFoundOr404.class)
   @FormParams(keys = ACTION, values = "DeleteDBSecurityGroup")
   ListenableFuture<Void> delete(@FormParam("DBSecurityGroupName") String name);
}
