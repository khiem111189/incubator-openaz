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

package org.apache.openaz.pepapi.std;

import org.apache.openaz.pepapi.PepRequest;
import org.apache.openaz.pepapi.PepRequestAttributes;
import org.apache.openaz.pepapi.Subject;
import org.apache.openaz.xacml.api.XACML3;

public class SubjectMapper extends CategoryContainerMapper {

    public SubjectMapper() {
        super(Subject.class);
    }

    @Override
    public void map(Object o, PepRequest pepRequest) {
        Subject s = (Subject) o;
        String id = s.getId();
        if (id == null) {
            id = getPepConfig().getDefaultSubjectId();
            if (id != null) {
                PepRequestAttributes resourceAttributes = pepRequest
                        .getPepRequestAttributes(XACML3.ID_SUBJECT_CATEGORY_ACCESS_SUBJECT);
                resourceAttributes.addAttribute(XACML3.ID_SUBJECT_SUBJECT_ID.stringValue(), (String) id);
            }
        }
        super.map(o, pepRequest);
    }
}