package com.ke.plugin;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.util.List;

/**
 * 自定义一个plugin, 使用前先打包(mvn clean install)再在别的项目通过plugin 引入
 */
@Mojo(name = "ke_plugin", defaultPhase = LifecyclePhase.PACKAGE)
public class PluginMojo extends AbstractMojo{
    @Parameter
    private String msg;
    @Parameter
    private List<String> actions;

    public void execute() throws MojoExecutionException, MojoFailureException {
        System.out.println("welcome user ke plugin, msg: "+ msg + " actions.size(): " + actions.size());
    }

}
