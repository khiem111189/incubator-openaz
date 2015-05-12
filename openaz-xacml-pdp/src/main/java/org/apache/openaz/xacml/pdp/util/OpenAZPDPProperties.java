/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 *
 */

/*
 *                        AT&T - PROPRIETARY
 *          THIS FILE CONTAINS PROPRIETARY INFORMATION OF
 *        AT&T AND IS NOT TO BE DISCLOSED OR USED EXCEPT IN
 *             ACCORDANCE WITH APPLICABLE AGREEMENTS.
 *
 *          Copyright (c) 2013 AT&T Knowledge Ventures
 *              Unpublished and Not for Publication
 *                     All Rights Reserved
 */
package com.att.research.xacmlatt.pdp.util;

import com.att.research.xacml.api.Identifier;
import com.att.research.xacml.std.IdentifierImpl;
import com.att.research.xacml.util.XACMLProperties;

public class ATTPDPProperties extends XACMLProperties {
    public static final String PROP_EVALUATIONCONTEXTFACTORY	= "xacml.att.evaluationContextFactory";
    public static final String PROP_COMBININGALGORITHMFACTORY	= "xacml.att.combiningAlgorithmFactory";
    public static final String PROP_FUNCTIONDEFINITIONFACTORY	= "xacml.att.functionDefinitionFactory";
    public static final String PROP_POLICYFINDERFACTORY			= "xacml.att.policyFinderFactory";
    public static final String PROP_POLICYFINDERFACTORY_COMBINEROOTPOLICIES = "xacml.att.policyFinderFactory.combineRootPolicies";

    public static final Identifier ID_POLICY_COMBINEDPERMITOVERRIDES = new IdentifierImpl("urn:com:att:xacml:3.0:policy-combining-algorithm:combined-permit-overrides");

    protected ATTPDPProperties() {
    }

}