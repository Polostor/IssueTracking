package org.issuetracking.service.hooks;

import org.guvnor.annotation.Hook;
import org.guvnor.hook.ParameterHook;

import java.util.*;
import javax.inject.Inject;
import org.issuetracking.view.iface.PrincipalBeanInterface;

@Hook( name = "principalHook" )
public class PrincipalHook implements ParameterHook {

    @Inject
    private PrincipalBeanInterface context;

    @Override
    public Map<String, Object> get() {
        return Collections.singletonMap( "pb", ( Object ) context );
    }
}
