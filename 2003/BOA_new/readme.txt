 ------------------------------------------------------------------------------
| This is a description of the BOA developer's team common directive structure |
| This structure is needed for ANT-tack to be used for project building with   |
| a minimal changing of initial properties of this task.                       |
 ------------------------------------------------------------------------------                                                                               |


ant		the directory for ANT tacks fro project building

classes		the directory where compiled classes should be placed
		(please never add this directory to CVS)

deploy		the directory where an EAR-file will be placed after 
		it creation by ANT-task
		(Please checkout to CVS just a fully tested EAR-file 
		to be tested by Testers Team. So, a Project Manager, 
		Team Leader or a separate build-engineer should better do it)

doc		any developer's documentation should be placed here

docroot		the web-server's docroot - all JSP whold be placed here

lib		the JAR ibrary

metadata 	the meta-information needed to create a valid EAR-archive

src		the sources of Java classes


