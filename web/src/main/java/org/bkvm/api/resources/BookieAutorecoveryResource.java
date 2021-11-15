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
package org.bkvm.api.resources;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.apache.http.message.BasicNameValuePair;
import org.bkvm.auth.UserRole;
import org.bkvm.bookkeeper.restapi.BookieApiResponse;
import org.bkvm.bookkeeper.restapi.HttpRequestUtils;
import org.bkvm.utils.BookkeeperApiEndpoint;

@Path("autorecovery")
@DeclareRoles({UserRole.Fields.Admin, UserRole.Fields.User})
public class BookieAutorecoveryResource extends AbstractBookkeeperResource {

    @GET
    @Secured
    @RolesAllowed(UserRole.Fields.Admin)
    @Path("trigger")
    @Produces(MediaType.APPLICATION_JSON)
    public BookieApiResponse triggerAutorecovery(@QueryParam("bookie_src") String bookie_src,
            @QueryParam("bookie_dest") String bookie_dest,
            @QueryParam("delete_cookie") boolean delete_cookie,
            @QueryParam("clusterId") int clusterId,
            @QueryParam("bookieId") String bookieId) throws IOException, InterruptedException, URISyntaxException, ExecutionException {

        Map<String, String> headers = new HashMap<>();
        headers.put("bookie_scr", bookie_src);
        headers.put("bookie_dest", bookie_dest);
        headers.put("delete_cookie", String.valueOf(delete_cookie));
        String bookieHttpServerUri = getBookkeeperManager().getHttpServerEndpoint(clusterId, bookieId);
        return HttpRequestUtils.sendPutRequest(bookieHttpServerUri, BookkeeperApiEndpoint.BOOKIE_AUTORECOVERY, headers, null);
    }

    @GET
    @Secured
    @RolesAllowed(UserRole.Fields.Admin)
    @Path("unreplicatedledgers")
    @Produces(MediaType.APPLICATION_JSON)
    public BookieApiResponse getUnreplicatedLedgersList(@QueryParam("missingreplica") String missingreplica,
            @QueryParam("excludingmissingreplica") String excludingmissingreplica,
            @QueryParam("clusterId") int clusterId,
            @QueryParam("bookieId") String bookieId) throws IOException, InterruptedException, URISyntaxException, ExecutionException {

        String bookieHttpServerUri = getBookkeeperManager().getHttpServerEndpoint(clusterId, bookieId);
        List parameters = new ArrayList();
        parameters.add(new BasicNameValuePair("missingreplica", missingreplica));
        parameters.add(new BasicNameValuePair("excludingmissingreplica", excludingmissingreplica));
        return HttpRequestUtils.sendGetRequest(bookieHttpServerUri, BookkeeperApiEndpoint.BOOKIE_LIST_UNDERREPLICATED, parameters);
    }

    @GET
    @Secured
    @RolesAllowed(UserRole.Fields.Admin)
    @Path("whoisauditor")
    @Produces(MediaType.APPLICATION_JSON)
    public BookieApiResponse getAuditor(@QueryParam("clusterId") int clusterId,
            @QueryParam("bookieId") String bookieId) throws IOException, InterruptedException, URISyntaxException, ExecutionException {

        String bookieHttpServerUri = getBookkeeperManager().getHttpServerEndpoint(clusterId, bookieId);
        return HttpRequestUtils.sendGetRequest(bookieHttpServerUri, BookkeeperApiEndpoint.BOOKIE_WHO_IS_AUDITOR, null);
    }

    @GET
    @Secured
    @RolesAllowed(UserRole.Fields.Admin)
    @Path("forcetriggeraudit")
    @Produces(MediaType.APPLICATION_JSON)
    public BookieApiResponse forceTriggerAudit(@QueryParam("clusterId") int clusterId,
            @QueryParam("bookieId") String bookieId) throws IOException, InterruptedException, URISyntaxException, ExecutionException {

        String bookieHttpServerUri = getBookkeeperManager().getHttpServerEndpoint(clusterId, bookieId);
        return HttpRequestUtils.sendPutRequest(bookieHttpServerUri, BookkeeperApiEndpoint.BOOKIE_TRIGGER_AUDIT, null, null);
    }

    @GET
    @Secured
    @RolesAllowed(UserRole.Fields.Admin)
    @Path("decommission")
    @Produces(MediaType.APPLICATION_JSON)
    public BookieApiResponse triggerAutorecovery(@QueryParam("bookie_src") String bookie_src,
            @QueryParam("clusterId") int clusterId,
            @QueryParam("bookieId") String bookieId) throws IOException, InterruptedException, URISyntaxException, ExecutionException {

        Map<String, String> headers = new HashMap<>();
        headers.put("bookie_scr", bookie_src);
        String bookieHttpServerUri = getBookkeeperManager().getHttpServerEndpoint(clusterId, bookieId);
        return HttpRequestUtils.sendPutRequest(bookieHttpServerUri, BookkeeperApiEndpoint.BOOKIE_DECOMMISSION, headers, null);
    }

    @GET
    @Secured
    @RolesAllowed(UserRole.Fields.Admin)
    @Path("bookierecoverydelay")
    @Produces(MediaType.APPLICATION_JSON)
    public BookieApiResponse getLostBookieRecoveryDelay(@QueryParam("clusterId") int clusterId,
            @QueryParam("bookieId") String bookieId) throws IOException, InterruptedException, URISyntaxException, ExecutionException {

        String bookieHttpServerUri = getBookkeeperManager().getHttpServerEndpoint(clusterId, bookieId);
        return HttpRequestUtils.sendGetRequest(bookieHttpServerUri, BookkeeperApiEndpoint.BOOKIE_LOST_RECOVERY_DELAY, null);
    }

    @POST
    @Secured
    @RolesAllowed(UserRole.Fields.Admin)
    @Path("bookierecoverydelay/update")
    @Produces(MediaType.APPLICATION_JSON)
    public BookieApiResponse triggerAutorecovery(long delay_seconds,
            @QueryParam("clusterId") int clusterId,
            @QueryParam("bookieId") String bookieId) throws IOException, InterruptedException, URISyntaxException, ExecutionException {

        Map<String, String> headers = new HashMap<>();
        headers.put("delay_seconds", String.valueOf(delay_seconds));
        String bookieHttpServerUri = getBookkeeperManager().getHttpServerEndpoint(clusterId, bookieId);
        return HttpRequestUtils.sendPostRequest(bookieHttpServerUri, BookkeeperApiEndpoint.BOOKIE_LOST_RECOVERY_DELAY, headers, null);
    }
}
