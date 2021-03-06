package org.apache.solr.core;
/**
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

import java.io.IOException;

import org.apache.lucene.index.IndexReader;
import org.apache.lucene.store.Directory;
import org.apache.solr.util.AbstractSolrTestCase;

public class AlternateIndexReaderTest extends AbstractSolrTestCase {

  public String getSchemaFile() {
    return "schema.xml";
  }

  public String getSolrConfigFile() {
    return "solrconfig-altdirectory.xml";
  }

  /**
   * Simple test to ensure that alternate IndexReaderFactory is being used.
   * 
   * @throws Exception
   */
  public void testAltReaderUsed() throws Exception {
    assertTrue(TestIndexReaderFactory.newReaderCalled);
  }

  static public class TestIndexReaderFactory extends IndexReaderFactory {

    static boolean newReaderCalled = false;

    public IndexReader newReader(Directory indexDir) throws IOException {
      TestIndexReaderFactory.newReaderCalled = true;
      return IndexReader.open(indexDir);
    }

    public IndexReader newReader(Directory indexDir, boolean readOnly)
        throws IOException {
      TestIndexReaderFactory.newReaderCalled = true;
      return IndexReader.open(indexDir, readOnly);
    }

  }

}
