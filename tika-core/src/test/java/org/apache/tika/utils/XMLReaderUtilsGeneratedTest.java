/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.tika.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.xml.sax.helpers.AttributesImpl;

/**
 * Tests issus de la génération ChatUniTest, corrigés après analyse
 * des erreurs de compilation et des assertions proposées.
 */
public class XMLReaderUtilsGeneratedTest {

    @Test
    public void testAttributPresent() {
        AttributesImpl attributs = new AttributesImpl();
        attributs.addAttribute("", "name", "name", "CDATA", "John");
        attributs.addAttribute("", "age", "age", "CDATA", "30");

        assertEquals("John", XMLReaderUtils.getAttrValue("name", attributs));
    }

    @Test
    public void testAttributAbsent() {
        AttributesImpl attributs = new AttributesImpl();
        attributs.addAttribute("", "name", "name", "CDATA", "John");

        assertNull(XMLReaderUtils.getAttrValue("age", attributs));
    }

    @Test
    public void testAucunAttribut() {
        AttributesImpl attributs = new AttributesImpl();

        assertNull(XMLReaderUtils.getAttrValue("name", attributs));
    }

    @Test
    public void testNomDifferent() {
        AttributesImpl attributs = new AttributesImpl();
        attributs.addAttribute("", "invalid", "invalid", "CDATA", "John");

        assertNull(XMLReaderUtils.getAttrValue("name", attributs));
    }

    @Test
    public void testRespectDeLaCasse() {
        AttributesImpl attributs = new AttributesImpl();
        attributs.addAttribute("", "Name", "Name", "CDATA", "John");

        assertNull(XMLReaderUtils.getAttrValue("name", attributs));
    }

    @Test
    public void testValeurNumeriqueSousFormeDeTexte() {
        AttributesImpl attributs = new AttributesImpl();
        attributs.addAttribute("", "id", "id", "CDATA", "123");

        assertEquals("123", XMLReaderUtils.getAttrValue("id", attributs));
    }

    @Test
    public void testValeurNulle() {
        AttributesImpl attributs = new AttributesImpl();
        attributs.addAttribute("", "name", "name", "CDATA", null);

        assertNull(XMLReaderUtils.getAttrValue("name", attributs));
    }
}
