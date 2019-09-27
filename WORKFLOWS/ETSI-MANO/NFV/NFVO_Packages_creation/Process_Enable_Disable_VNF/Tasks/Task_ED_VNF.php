<?php
require_once '/opt/fmc_repository/Process/Reference/Common/common.php';
include "/opt/fmc_repository/Process/ETSI-MANO/vendor/autoload.php";

use Ubiqube\EtsiMano\VnfPkgSol005;
use Ubiqube\EtsiMano\ManoException;

function list_args()
{
	create_var_def('vnfPkgId', 'string');
	create_var_def('state', 'Boolean');
}

$url = get_url_from_device($context['device_id']);
$vnfPkgApi = new VnfPkgSol005($url);
$state = $context['state'];
try {
	$vnfPkgApi->setOperationalState($context['vnfPkgId'], $state);
} catch (ManoException $e) {
	task_error($e->getMessage());
}

unset($context['vnfPkgId']);
unset($context['state']);

task_exit(ENDED, "Task OK");
