package org.apache.amaterasu.frameworks.spark.dispatcher.runners.providers

import org.apache.amaterasu.common.configuration.ClusterConfig
import org.apache.amaterasu.common.dataobjects.ActionData
import org.apache.amaterasu.sdk.frameworks.RunnerSetupProvider

class SparkShellScalaRunnerProvider extends RunnerSetupProvider {

  private var conf: ClusterConfig = _

  override def getCommand(jobId: String, actionData: ActionData, env: String, executorId: String, callbackAddress: String): String =
    s"$$SPARK_HOME/bin/spark-shell ${actionData.getSrc} --jars spark-runtime-${conf.version}.jar"

  override def getRunnerResources: Array[String] =
    Array[String]()

  def getActionResources(jobId: String, actionData: ActionData): Array[String] =
    Array[String](s"$jobId/${actionData.getName}/${actionData.getSrc}")

  override def getActionDependencies(jobId: String, actionData: ActionData): Array[String] =  Array[String]()

}

object SparkShellScalaRunnerProvider {
  def apply(conf: ClusterConfig): SparkShellScalaRunnerProvider = {
    val result = new SparkShellScalaRunnerProvider
    result.conf = conf
    result
  }
}