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

public class ParseException extends RuntimeException {

    public ParseException() {
        super();
    }

    public ParseException(String msg, Throwable t, boolean enableSuppression, boolean writableStackTrace) {
        super(msg, t, enableSuppression, writableStackTrace);
    }

    public ParseException(String msg, Throwable t) {
        super(msg, t);
    }

    public ParseException(String msg) {
        super(msg);
    }

    public ParseException(Throwable t) {
        super(t);
    }

}
