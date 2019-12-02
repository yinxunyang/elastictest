package com.bestvike.portal.service;

import java.util.Date;

public interface TaskService {
    public Date selectTimestamp();
    public int updateTimestamp(Date date);
}
