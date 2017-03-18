package com.basic.bigdata.dao;

/**
 * Created by dell-pc on 2016/4/24.
 */
import org.hibernate.Session;

import java.io.Serializable;

public abstract interface HibernateHandler extends Serializable {
    public abstract Object doInHibernate(Session paramSession);
}
