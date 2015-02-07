package com.github.stephanenicolas.lxglifecycle

import com.github.stephanenicolas.morpheus.AbstractMorpheusPlugin;
import javassist.build.IClassTransformer;
import org.gradle.api.Project;

/**
 * @author SNI
 */
public class LxgLifecyclePlugin extends AbstractMorpheusPlugin {

  @Override
  public IClassTransformer[] getTransformers(Project project) {
    return new LxgLifeCycleProcessor(project.lxglifecycle.debug);
  }

  @Override
  protected void configure(Project project) {
    project.dependencies {
      provided 'com.github.stephanenicolas.lxglifecycle:lxglifecycle-annotations:1.0.1'
    }
  }

  @Override
  protected Class getPluginExtension() {
    LxgLifecyclePluginExtension
  }

  @Override
  protected String getExtension() {
    "lxglifecycle"
  }

  @Override
  public boolean skipVariant(def variant) {
    return variant.name.contains('release')
  }
}
