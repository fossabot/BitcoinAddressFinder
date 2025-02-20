// @formatter:off
/**
 * Copyright 2022 Bernard Ladenthin bernard.ladenthin@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
// @formatter:on
package net.ladenthin.bitcoinaddressfinder;

import static java.lang.Boolean.TRUE;
import java.lang.reflect.Field;
import static org.hamcrest.Matchers.is;
import org.junit.Assume;

public class OpenCLPlatform {
    public boolean isOpenCLAvailable() {
        try {
            Class.forName("org.jocl.CL");
            Field field = org.jocl.CL.class.getDeclaredField("nativeLibraryLoaded");
            field.setAccessible(true);
            return field.getBoolean(null);
        } catch(java.lang.UnsatisfiedLinkError e) {
            return false;
        } catch (java.lang.NoClassDefFoundError e) {
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public void assumeOpenCLAvailable() {
        Assume.assumeThat("OpenCL available", isOpenCLAvailable(), is(TRUE));
    }
}
