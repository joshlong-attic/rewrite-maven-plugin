package io.spring.maven;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
	* The goal of this Maven plugin is to support applying rewrite rules
	* to the code represented by a current project. This Maven plugin
	* <a href="https://github.com/Netflix-Skunkworks/rewrite"> supports
	* the Netflix Rewrite project</a> and is meant to mirror the support for Gradle in
	* <a href="https://github.com/spring-gradle-plugins/rewrite-gradle">the Rewrite Gradle plugin</a>.
	*
	* @author <a href="mailto:josh@joshlong.com">Josh Long</a>
	*
	*/
@Mojo(name = "rewrite", defaultPhase = LifecyclePhase.PROCESS_SOURCES)
public class RewriteMojo extends AbstractMojo {

		@Parameter(defaultValue = "${project.build.directory}", property = "outputDir", required = true)
		private File outputDirectory;

		/**
			* Run the following method using the following Maven incantation.
			*
			* <CODE> mvn io.spring.maven:rewrite-maven-plugin:rewrite</CODE>
			*/
		@Override
		public void execute() throws MojoExecutionException, MojoFailureException {
				File touch = new File(this.outputDirectory, "touch.txt");
				File parent = touch.getParentFile();
				if (parent.exists() || parent.mkdirs()) {
						try (FileWriter fw = new FileWriter(touch)) {
								fw.write("hello, world!");
								getLog().info("wrote a file to " + touch.getAbsolutePath() + ".");
						}
						catch (IOException ex) {
								getLog().error("couldn't write the file out.", ex);
						}
				}
		}
}
