//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-661 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2009.02.03 at 07:14:18 PM EST 
//


package com.sun.grizzly.http.servlet.xsd;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 
 * 	The dispatcher has four legal values: FORWARD, REQUEST, INCLUDE,
 * 	and ERROR. A value of FORWARD means the Filter will be applied
 * 	under RequestDispatcher.forward() calls.  A value of REQUEST
 * 	means the Filter will be applied under ordinary client calls to
 * 	the path or servlet. A value of INCLUDE means the Filter will be
 * 	applied under RequestDispatcher.include() calls.  A value of
 * 	ERROR means the Filter will be applied under the error page
 * 	mechanism.  The absence of any dispatcher elements in a
 * 	filter-mapping indicates a default of applying filters only under
 * 	ordinary client calls to the path or servlet.
 * 
 *       
 * 
 * <p>Java class for dispatcherType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dispatcherType">
 *   &lt;simpleContent>
 *     &lt;restriction base="&lt;http://java.sun.com/xml/ns/j2ee>string">
 *     &lt;/restriction>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dispatcherType")
public class DispatcherType
    extends String
{


}
