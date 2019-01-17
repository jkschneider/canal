package io.pivotal.kanal.json

import io.pivotal.kanal.fluent.Stages
import io.pivotal.kanal.model.*
import org.intellij.lang.annotations.Language

object BasicPipelineTemplate {

    @Language("JSON")
    @JvmStatic
    val json = """
    {
        "schema" : "v2",
        "variables" : [
            {
                "type" : "int",
                "defaultValue" : 42,
                "description" : "The time a wait stage shall pauseth",
                "name" : "waitTime"
            }
        ],
        "id" : "newSpelTemplate",
        "protect" : false,
        "metadata" : {
            "name" : "Variable Wait",
            "description" : "A demonstrative Wait Pipeline.",
            "owner" : "example@example.com",
            "scopes" : ["global"]
        },
        "pipeline": {
            "lastModifiedBy" : "anonymous",
            "updateTs" : "0",
            "parameterConfig" : [],
            "limitConcurrent": false,
            "keepWaitingPipelines": false,
            "description" : "",
            "triggers" : [],
            "notifications" : [],
            "stages" : [
                {
                    "waitTime" : "$\\{ templateVariables.waitTime }",
                    "name": "My Wait Stage",
                    "type" : "wait",
                    "refId" : "1",
                    "requisiteStageRefIds": [],
                    "comments": ""
                }
            ],
            "expectedArtifacts": [],
            "appConfig": {}
        }
    }
        """.trimMargin()

    @JvmStatic
    val model = PipelineTemplate(
            "newSpelTemplate",
            Metadata(
                    "Variable Wait",
                    "A demonstrative Wait Pipeline.",
                    "example@example.com"
            ),
            listOf(
                    IntegerType(
                            "waitTime",
                            "The time a wait stage shall pauseth",
                            42
                    )
            ),
            Pipeline(
                    stageGraph = Stages.first(WaitStage(
                            "My Wait Stage",
                            "$\\{ templateVariables.waitTime }"
                    )).stageGraph
            )
    )
}
