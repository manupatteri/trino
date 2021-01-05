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
package io.trino.plugin.hive;

import io.trino.execution.DynamicFilterConfig;
import io.trino.testing.AbstractTestJoinQueries;
import io.trino.testing.QueryRunner;
import org.testng.annotations.Test;

import static com.google.common.base.Verify.verify;
import static io.trino.tpch.TpchTable.getTables;

/**
 * @see TestHiveDistributedJoinQueriesWithoutDynamicFiltering for tests with dynamic filtering enabled
 */
public class TestHiveDistributedJoinQueries
        extends AbstractTestJoinQueries
{
    @Override
    protected QueryRunner createQueryRunner()
            throws Exception
    {
        verify(new DynamicFilterConfig().isEnableDynamicFiltering(), "this class assumes dynamic filtering is enabled by default");
        return HiveQueryRunner.builder()
                .setInitialTables(getTables())
                .build();
    }

    @Test
    public void verifyDynamicFilteringEnabled()
    {
        assertQuery(
                "SHOW SESSION LIKE 'enable_dynamic_filtering'",
                "VALUES ('enable_dynamic_filtering', 'true', 'true', 'boolean', 'Enable dynamic filtering')");
    }
}
