package com.bbva.mneo.lib.r001;

import com.bbva.elara.configuration.manager.application.ApplicationConfigurationService;
import com.bbva.elara.domain.transaction.Context;
import com.bbva.elara.domain.transaction.ThreadContext;
import javax.annotation.Resource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.Advised;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:/META-INF/spring/MNEOR001-app.xml",
		"classpath:/META-INF/spring/MNEOR001-app-test.xml",
		"classpath:/META-INF/spring/MNEOR001-arc.xml",
		"classpath:/META-INF/spring/MNEOR001-arc-test.xml" })
public class MNEOR001Test {

	private static final Logger LOGGER = LoggerFactory.getLogger(MNEOR001Test.class);

	@Spy
	private Context context;

	@Resource(name = "mneoR001")
	private MNEOR001 mneoR001;

	@Resource(name = "applicationConfigurationService")
	private ApplicationConfigurationService applicationConfigurationService;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		context = new Context();
		ThreadContext.set(context);
		getObjectIntrospection();
	}
	
	private Object getObjectIntrospection() throws Exception{
		Object result = this.mneoR001;
		if(this.mneoR001 instanceof Advised){
			Advised advised = (Advised) this.mneoR001;
			result = advised.getTargetSource().getTarget();
		}
		return result;
	}
	
	@Test
	public void executeTest(){
		LOGGER.info("Executing the test...");
		//mneoR001.execute();
		Assert.assertEquals(0, context.getAdviceList().size());
	}
	
}
