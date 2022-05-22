package io.metadb.sql.parser;

import org.junit.jupiter.api.Test;

/**
 * @author jinhai
 * @date 2021/11/01
 */
public class TestSqlParser {
    private static final SqlParser SQL_PARSER = new SqlParser();

    @Test
    public void testQuerySpecification() {
        SQL_PARSER.parse("select 1+2*3");
    }
}
