/*
 * Copyright (C) 2015 John Hyun
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.typelibrary.dns;

public final class Question {

    private final String qname;
    private final RecordType questionType;
    private final QClass questionClass;

    public Question(String qname, RecordType questionType, QClass questionClass) {
        if (qname == null)
            throw new IllegalArgumentException("Qname cannot be null");
        if (questionType == null)
            throw new IllegalArgumentException("Question type cannot be null");
        if (questionClass == null)
            throw new IllegalArgumentException("Question class cannot be null");
        this.qname = qname;
        this.questionType = questionType;
        this.questionClass = questionClass;
    }

    public final String getQname() {
        return qname;
    }

    public final RecordType getQuestionType() {
        return questionType;
    }

    public final QClass getQuestionClass() {
        return questionClass;
    }

    public final String toString() {
        return "qname=" + qname + ", type=" + questionType + ", class=" + questionClass;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((qname == null) ? 0 : qname.hashCode());
        result = prime * result + ((questionClass == null) ? 0 : questionClass.hashCode());
        result = prime * result + ((questionType == null) ? 0 : questionType.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Question other = (Question) obj;
        if (qname == null) {
            if (other.qname != null)
                return false;
        } else if (!qname.equals(other.qname))
            return false;
        if (questionClass == null) {
            if (other.questionClass != null)
                return false;
        } else if (!questionClass.equals(other.questionClass))
            return false;
        if (questionType == null) {
            if (other.questionType != null)
                return false;
        } else if (!questionType.equals(other.questionType))
            return false;
        return true;
    }
    
}
