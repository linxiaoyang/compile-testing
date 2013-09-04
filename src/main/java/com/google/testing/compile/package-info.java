/*
 * Copyright (C) 2013 Google, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * This package contains utilities that facilitate testing {@code javac} compilation with
 * {@link org.truth0.Truth}. Particularly, this enables quick, small tests of
 * {@linkplain javax.annotation.processing.Processor annotation processors} without forking
 * {@code javac} or creating separate integration test projects.
 *
 * <p>The simplest invocation looks like this: <pre>   {@code
 *
 *   ASSERT.about(javaSource())
 *       .that(JavaFileObjects.forSourceString("HelloWorld", "final class HelloWorld {}"))
 *       .compilesWithoutError();
 * }</pre>
 *
 * <p>The above code snippet tests that the provide source compiles without error. There is not much
 * utility in testing compilation for simple sources, but the API also allows for the addition of
 * {@linkplain javax.annotation.processing.Processor annotation processors}. Here is the same
 * example with a processor: <pre>   {@code
 *
 *   ASSERT.about(javaSource())
 *       .that(JavaFileObjects.forSourceString("HelloWorld", "final class HelloWorld {}"))
 *       .processedWith(new MyAnnotationProcessor())
 *       .compilesWithoutError();
 * }</pre>
 *
 * <p>This snippet tests that the given source <i>and all sources generated by the processor</i>
 * compile without error. Any exception thrown by the annotation processor will be (wrapped by the
 * compiler and) thrown by the tester.
 *
 * <p>Further tests can be applied to compilation results as well. For example, the following
 * snippet tests that a file (a class path resource) processed with an annotation processor
 * generates a source file equivalent to a golden file: <pre>   {@code
 *
 *   ASSERT.about(javaSource())
 *       .that(JavaFileObjects.forResouce("HelloWorld.java"))
 *       .processedWith(new MyAnnotationProcessor())
 *       .compilesWithoutError()
 *       .and().generatesSources(JavaFileObjects.forResouce("GeneratedHelloWorld.java"));
 * }</pre>
 *
 * <p>Finally, negative tests are possible as well.  The following tests that a processor adds an
 * error to a source file: <pre>   {@code
 *
 *   JavaFileObject fileObject = JavaFileObjects.forResouce("HelloWorld.java");
 *   ASSERT.about(javaSource())
 *       .that(fileObject)
 *       .processedWith(new NoHelloWorld())
 *       .failsToCompile()
 *       .withErrorContaining("No types named HelloWorld!").in(fileObject).onLine(23).atColumn(5);
 * }</pre>
 */
package com.google.testing.compile;
