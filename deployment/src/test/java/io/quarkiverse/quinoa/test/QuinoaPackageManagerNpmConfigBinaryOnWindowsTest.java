package io.quarkiverse.quinoa.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import io.quarkiverse.quinoa.deployment.testing.QuinoaQuarkusUnitTest;
import io.quarkus.test.QuarkusUnitTest;

public class QuinoaPackageManagerNpmConfigBinaryOnWindowsTest {

    @RegisterExtension
    static final QuarkusUnitTest config = QuinoaQuarkusUnitTest.create()
            .osName("Windows One")
            .toQuarkusUnitTest()
            .overrideConfigKey("quarkus.quinoa.force-install", "true")
            .assertLogRecords(l -> {
                assertThat(l).anySatisfy(s -> {
                    assertThat(s.getMessage()).isEqualTo("Running Quinoa package manager install command: %s");
                    assertThat(s.getParameters()[0]).isEqualTo("npm.cmd install");
                });
            })
            .assertException(e -> assertThat(e).isNotNull());

    @Test
    public void testQuinoa() {
        // test exception
    }

}
