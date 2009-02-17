/*
 * 
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * 
 * Copyright 2007-2008 Sun Microsystems, Inc. All rights reserved.
 * 
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License. You can obtain
 * a copy of the License at https://glassfish.dev.java.net/public/CDDL+GPL.html
 * or glassfish/bootstrap/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 * 
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at glassfish/bootstrap/legal/LICENSE.txt.
 * Sun designates this particular file as subject to the "Classpath" exception
 * as provided by Sun in the GPL Version 2 section of the License file that
 * accompanied this code.  If applicable, add the following below the License
 * Header, with the fields enclosed by brackets [] replaced by your own
 * identifying information: "Portions Copyrighted [year]
 * [name of copyright owner]"
 * 
 * Contributor(s):
 * 
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
 *
 */

package com.sun.grizzly.cometd.bayeux;
/**
 * Bayeux Subscribe implementation. 
 * See http://svn.xantus.org/shortbus/trunk/bayeux/protocol.txt for the technical
 * details.
 *
 *	// Subscribing and unsubscribing to channel is straightforward. Subscribing:
 *
 *	//-----------------
 *	// CLIENT -> SERVER
 *	//-----------------
 *
 *	[
 *		{
 *			"channel":		"/meta/subscribe",
 *			"subscription":	"/some/other/channel",
 *			// optional
 *			"authToken":	"SOME_NONCE_PREVIOUSLY_PROVIDED_BY_SERVER"
 *		}
 *		// , ...
 *	]
 *
 *	// response to subscription:
 *
 *	//-----------------
 *	// SERVER -> CLIENT
 *	//-----------------
 *
 *	[
 *		{
 *			"channel":		"/meta/subscribe",
 *			"subscription":	"/some/other/channel",
 *			"successful":	true,
 *			"advice":		{
 *				"transport":	{
 *					retry: true, // or false
 *				}
 *			},
 *			"clientId":		"SOME_UNIQUE_CLIENT_ID",
 *			"error":		"",
 *			// optional
 *			"authToken":	"SOME_NONCE"
 *		}
 *		// , ...
 *	]
 * @author Jeanfrancois Arcand
 */
abstract class Subscribe extends VerbBase{
    public static final String META_SUBSCRIBE = "/meta/subscribe";
    
    protected String subscription;
    
    public Subscribe() {
        type = Verb.Type.SUBSCRIBE;
        metaChannel = META_SUBSCRIBE;
    }

    public String getSubscription() {
        return subscription;
    }

    public void setSubscription(String subscription) {
        this.subscription = subscription;
    }

    @Override
    public boolean isValid() {
        return (clientId != null && subscription != null &&
                getMetaChannel().equals(getChannel()));
    }

    /**
     * @param isResponse   indicates whether it is a response
     * @param timestamp   for UnsubscribeResponse
     * @return Body as a string
     */
    protected String getBody(boolean isResponse, String timestamp) { 
        StringBuilder sb = new StringBuilder(
                getJSONPrefix() + "{"
                + "\"channel\":\"" + channel + "\""
                );
        if (isResponse) {
            sb.append(",\"successful\":").append(successful);
        }
        sb.append(",\"clientId\":\"").append(clientId).append("\"");
        sb.append(",\"subscription\":\"").append(subscription).append("\"");
        if (isResponse && error != null) {
            sb.append(",\"error\":\"").append(error).append("\"");
        }
        if (isResponse && advice != null) {
            sb.append(",").append(advice.toJSON());
        }
        if (ext != null) {
            sb.append(",").append(ext.toJSON());
        }
        if (id != null) {
            sb.append(",\"id\":\"").append(id).append("\"");
        }
        if (timestamp != null) {
            sb.append(",\"timestamp\":\"").append(timestamp).append("\"");
        }
        sb.append("}").append(getJSONPostfix());
        return sb.toString();
    }
}
