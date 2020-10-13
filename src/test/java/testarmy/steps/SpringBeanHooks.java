package testarmy.steps;

import io.cucumber.java.Before;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import testarmy.utils.DriverProvider;

public class SpringBeanHooks implements BeanFactoryAware {

    private BeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Before()
    public void startTransaction() {
        obtainDriverProvider().prepareForNewTest();
    }

    public DriverProvider obtainDriverProvider() {
        return beanFactory.getBean(DriverProvider.class);
    }

}
