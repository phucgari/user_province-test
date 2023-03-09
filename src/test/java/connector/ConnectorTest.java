package connector;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConnectorTest {
    private final String jdbcURL = "jdbc:mysql://localhost:3306/user_province_demo?";
    Connector connector=new Connector(jdbcURL,"root","123456");
    @Test
    void testFlush(){
        connector.flush();
    }
}