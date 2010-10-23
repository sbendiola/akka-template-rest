package samples

import se.scalablesolutions.akka.actor.SupervisorFactory
import se.scalablesolutions.akka.config.ScalaConfig._
import se.scalablesolutions.akka.actor.Actor

class Boot {
	
	val factory = SupervisorFactory(
		SupervisorConfig(
			RestartStrategy(OneForOne, 3, 100, List(classOf[Exception])),
			Supervise(Actor.actorOf[HelloActor], LifeCycle(Permanent)) :: Nil))


	factory.newInstance.start
}
