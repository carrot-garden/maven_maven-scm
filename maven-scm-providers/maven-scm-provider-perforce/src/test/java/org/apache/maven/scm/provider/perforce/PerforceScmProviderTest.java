package org.apache.maven.scm.provider.perforce;

/*
 * Copyright 2001-2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.apache.maven.scm.ScmTestCase;
import org.apache.maven.scm.provider.perforce.repository.PerforceScmProviderRepository;
import org.apache.maven.scm.repository.ScmRepository;

/**
 * @author <a href="mailto:trygvis@inamo.no">Trygve Laugst&oslash;l</a>
 * @version $Id$
 */
public class PerforceScmProviderTest
    extends ScmTestCase
{
    public void testParseConnection()
        throws Exception
    {
        ScmRepository repo = makeScmRepository( "scm:perforce://depot/projects/pathname" );

        PerforceScmProviderRepository p4Repo = (PerforceScmProviderRepository)repo.getProviderRepository();

        assertNull( p4Repo.getHost() );

        assertEquals( 0, p4Repo.getPort() );

        assertNull( p4Repo.getUser() );

        assertNull( p4Repo.getPassword() );

        assertEquals( "//depot/projects/pathname", p4Repo.getPath() );
    }

    public void testParseConnectionWithUsername()
        throws Exception
    {
        ScmRepository repo = makeScmRepository( "scm:perforce:username@//depot/projects/pathname" );

        PerforceScmProviderRepository p4Repo = (PerforceScmProviderRepository) repo.getProviderRepository();

        assertNull( p4Repo.getHost() );

        assertEquals( 0, p4Repo.getPort() );

        assertEquals( "username", p4Repo.getUser() );

        assertNull( p4Repo.getPassword() );

        assertEquals( "//depot/projects/pathname", p4Repo.getPath() );
    }

    public void testParseConnectionWithHostPortAndUsername()
        throws Exception
    {
        ScmRepository repo = makeScmRepository( "scm:perforce:host:1234:username@//depot/projects/pathname" );

        PerforceScmProviderRepository p4Repo = (PerforceScmProviderRepository) repo.getProviderRepository();

        assertEquals( "host", p4Repo.getHost() );

        assertEquals( 1234, p4Repo.getPort() );

        assertEquals( "username", p4Repo.getUser() );

        assertNull( p4Repo.getPassword() );

        assertEquals( "//depot/projects/pathname", p4Repo.getPath() );
    }

    public void testParseConnectionWithHostAndPort()
        throws Exception
    {
        ScmRepository repo = makeScmRepository( "scm:perforce:host:1234://depot/projects/pathname" );

        PerforceScmProviderRepository p4Repo = (PerforceScmProviderRepository) repo.getProviderRepository();

        assertEquals( "host", p4Repo.getHost() );

        assertEquals( 1234, p4Repo.getPort() );

        assertNull( p4Repo.getUser() );

        assertNull( p4Repo.getPassword() );

        assertEquals( "//depot/projects/pathname", p4Repo.getPath() );
    }

    // TODO: Add more tests for invalid connection strings.
}