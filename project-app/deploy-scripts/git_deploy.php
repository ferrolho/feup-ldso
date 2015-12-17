<?php

try {
	$payload = json_decode(file_get_contents('php://input'));
} catch(Exception $e) {
	exit(0);
}

// log the request
file_put_contents('logs/github.txt', print_r($payload, TRUE), FILE_APPEND);

if ($payload->ref === 'refs/heads/master') {
	echo 'deploying from github', PHP_EOL;

	// path to your site deployment script
	exec('deploy_wrapper');
}

echo 'git_deploy.php done!', PHP_EOL;
