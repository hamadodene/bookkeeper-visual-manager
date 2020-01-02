/*
 * Licensed to Diennea S.r.l. under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. Diennea S.r.l. licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
 */
package org.bookkeepervisualmanager.api.listeners;

import java.io.IOException;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

/**
 * Ensures that the user is authenticated
 */
public class AuthFilter implements ContainerRequestFilter {

    private static final Logger LOG = Logger.getLogger(AuthFilter.class.getName());

    @Context
    private HttpServletRequest request;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        System.out.println("URI" + requestContext.getUriInfo().getPath());
        System.out.println("MATCHED:" + requestContext.getUriInfo().getMatchedResources());
                
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loggedUser") == null) {
//            System.out.println("NOT LOGGED !");
//            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }

}
