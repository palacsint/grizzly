/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2006-2010 Oracle and/or its affiliates. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License.  You can
 * obtain a copy of the License at
 * https://glassfish.dev.java.net/public/CDDL+GPL_1_1.html
 * or packager/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at packager/legal/LICENSE.txt.
 *
 * GPL Classpath Exception:
 * Oracle designates this particular file as subject to the "Classpath"
 * exception as provided by Oracle in the GPL Version 2 section of the License
 * file that accompanied this code.
 *
 * Modifications:
 * If applicable, add the following below the License Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyright [year] [name of copyright owner]"
 *
 * Contributor(s):
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */
package org.glassfish.grizzly.config;

import java.io.IOException;

import org.glassfish.grizzly.config.dom.NetworkListener;

/**
 * <p>The GrizzlyServiceListener is responsible of mapping incoming requests to the proper Container or Grizzly
 * extensions. Registered Containers can be notified by Grizzly using three mode:</p>
 *
 * <ul> <li>At the transport level: Containers can be notified when TCP, TLS or UDP requests are mapped to them.</li>
 * <li>At the protocol level: Containers can be notified when protocols (ex: SIP, HTTP) requests are mapped to
 * them.</li> </li>At the requests level: Containers can be notified when specific patterns requests are mapped to
 * them.</li> <ul>
 *
 * @author Jeanfrancois Arcand
 * @author Justin Lee
 */
public interface GrizzlyListener {
    
    public void start() throws IOException;

    public void stop() throws IOException;

    public String getName();

    public int getPort();

    /*
    * Configures the given grizzlyListener.
    *
    * @param grizzlyListener The grizzlyListener to configure
    * @param httpProtocol The Protocol that corresponds to the given grizzlyListener
    * @param isSecure true if the grizzlyListener is security-enabled, false otherwise
    * @param httpServiceProps The httpProtocol-service properties
    * @param isWebProfile if true - just HTTP protocol is supported on port,
    *        false - port unification will be activated
    */
    // TODO: Must get the information from domain.xml Config objects.
    // TODO: Pending Grizzly issue 54
    public void configure(NetworkListener networkListener);
}