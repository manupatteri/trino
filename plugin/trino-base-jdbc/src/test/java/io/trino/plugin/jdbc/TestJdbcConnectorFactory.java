/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.trino.plugin.jdbc;

import io.trino.plugin.jdbc.credential.CredentialProviderModule;
import io.trino.spi.connector.ConnectorFactory;
import io.trino.testing.TestingConnectorContext;
import org.testng.annotations.Test;

import static io.airlift.configuration.ConfigurationAwareModule.combine;

public class TestJdbcConnectorFactory
{
    @Test
    public void test()
    {
        ConnectorFactory connectorFactory = new JdbcConnectorFactory(
                "test",
                combine(
                        new CredentialProviderModule(),
                        new ExtraCredentialsBasedJdbcIdentityCacheMappingModule(),
                        new TestingH2JdbcModule()));

        connectorFactory.create("test", TestingH2JdbcModule.createProperties(), new TestingConnectorContext());
    }
}
