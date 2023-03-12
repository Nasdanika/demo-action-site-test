package org.nasdanika.demo.action.tests;

import java.io.File;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.junit.jupiter.api.Test;
import org.nasdanika.html.model.app.gen.ActionSiteGenerator;
import org.nasdanika.ncore.util.SemanticInfo;

public class TestActionSiteGenerator {
	
	@Test
	public void generateActionSite() throws Exception {
		//new DrawioSemanticMappingGeneratorRefactored().generate();

		URI actionModelURI = URI.createFileURI(new File("../demo-action-site/actions.yml").getAbsolutePath());
			
		String pageTemplateResource = "../demo-action-site/page-template.yml";
		URI pageTemplateURI = URI.createFileURI(new File(pageTemplateResource).getAbsolutePath());//.appendFragment("/");
		
		String siteMapDomain = "https://docs.nasdanika.org/demo-action-site";
//		URI semanticMapURI = URI.createURI("https://docs.nasdanika.org/demo-action-site/semantic-map.json");				
		
		ActionSiteGenerator actionSiteGenerator = new ActionSiteGenerator() {
			
//			Map<ModelElement, Label> semanticMap = new LinkedHashMap<>();
			
			@Override
			protected Iterable<Entry<SemanticInfo, ?>> semanticInfoSource(ResourceSet resourceSet) {
				// TODO Load external info
				return super.semanticInfoSource(resourceSet);
			}
						
//			@Override
//			protected void buildRegistry(Action action, Map<EObject, Label> registry) {
//				registry.putAll(semanticMap);
//				super.buildRegistry(action, registry);
//			}
//			
//			@Override
//			protected boolean isSemanticInfoLink(Link link) {
//				return semanticMap.values().contains(link);
//			}
			
		};
		
		Map<String, Collection<String>> errors = actionSiteGenerator.generate(
				actionModelURI, 
				pageTemplateURI, 
				"https://nasdanika.org", 
				new File("target/action-site-generator"), 
				new File("target/action-site-generator-work-dir"), 
				false);
				
		int errorCount = 0;
		for (Entry<String, Collection<String>> ee: errors.entrySet()) {
			System.err.println(ee.getKey());
			for (String error: ee.getValue()) {
				System.err.println("\t" + error);
				++errorCount;
			}
		}
		
		System.out.println("There are " + errorCount + " site errors");
		
//		if (!errors.isEmpty()) {
//			fail("There are problems with pages: " + errorCount);
//		}		
	}

}
