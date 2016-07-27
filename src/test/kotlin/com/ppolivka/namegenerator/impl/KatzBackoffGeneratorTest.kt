package com.ppolivka.namegenerator.impl

import com.ppolivka.namegenerator.Generator
import org.junit.Assert.assertNotNull
import org.junit.Test

/**
 * Test of KatzBackoffGenerator
 *
 * @author ppolivka
 * @since 1.0
 */
class KatzBackoffGeneratorTest {

  var generator: Generator = KatzBackoffGenerator(setOf("zabcdefgh"), 2, 1.0f)

  @Test
  fun generate() {
    val generatedValue = generator.generate()
    assertNotNull(generatedValue)
  }

}
