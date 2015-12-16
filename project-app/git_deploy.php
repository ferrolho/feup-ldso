<?php
try
{
  $payload = json_decode($_REQUEST['payload']);
}
catch(Exception $e)
{
  exit(0);
}

//log the request
file_put_contents('logs/github.txt', print_r($payload, TRUE), FILE_APPEND);

if ($payload->ref === 'refs/heads/master')
{
  echo 'deploying from github';

  // path to your site deployment script
  exec('~/git/feup-ldso/project-app/deploy.sh');
}

echo 'git_deploy.php done!';
