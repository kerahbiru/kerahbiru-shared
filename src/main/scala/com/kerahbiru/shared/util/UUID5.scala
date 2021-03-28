package com.kerahbiru.shared.util

import java.util.UUID
import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@js.native
@JSImport("uuid", JSImport.Namespace)
object UUID5 extends js.Object {

  def v5(x: String, nameSpace: String): String = js.native

//  def fromSeed(x: String, nameSpace: String): UUID = UUID.fromString(v5(x, nameSpace))

}
